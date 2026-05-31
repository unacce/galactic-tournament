import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { SpeciesService } from '../../core/services/species.service';
import { Species as SpeciesModel } from '../../models/species.model';

@Component({
  selector: 'app-species',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './species.html',
  styleUrl: './species.css',
})
export class SpeciesComponent implements OnInit {
  successMessage = '';

  errorMessage = '';

  speciesList: SpeciesModel[] = [];

  newSpecies = {
    name: '',
    powerLevel: 0,
    specialAbility: '',
  };

  constructor(
    private speciesService: SpeciesService,
    private cdr: ChangeDetectorRef,
    private toastr: ToastrService,
  ) {}

  ngOnInit(): void {
    this.loadSpecies();
  }

  loadSpecies(): void {
    this.speciesService.getAll().subscribe({
      next: (response) => {
        this.speciesList = response;

        this.cdr.detectChanges();
      },

      error: (error) => {
        this.successMessage = '';

        this.errorMessage = error.error.error?.message ?? 'Error al cargar las especies';

        this.cdr.detectChanges();
      },
    });
  }

  createSpecies(): void {
    this.speciesService.create(this.newSpecies).subscribe({
      next: () => {
        this.toastr.success('Especie creada correctamente', 'Success');

        this.errorMessage = '';

        this.loadSpecies();

        this.clearForm();

        this.clearMessages();

        this.cdr.detectChanges();
      },

      error: (error) => {
        this.toastr.error(error?.error.message ?? 'Error inesperado', 'Error');
        this.cdr.detectChanges();
      },
    });
  }

  private clearForm(): void {
    this.newSpecies = {
      name: '',
      powerLevel: 0,
      specialAbility: '',
    };
  }

  private clearMessages(): void {
    setTimeout(() => {
      this.successMessage = '';
      this.errorMessage = '';

      this.cdr.detectChanges();
    }, 3000);
  }
}
