import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SpeciesService } from '../../core/services/species.service';
import { BattleService } from '../../core/services/battle.service';
import { Species } from '../../models/species.model';
import { BattleResponse } from '../../models/battle-response.model';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-battle',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './battle.html',
  styleUrl: './battle.css',
})
export class BattleComponent implements OnInit {
  speciesList: Species[] = [];

  battleRequest = {
    speciesOneId: 0,
    speciesTwoId: 0,
  };

  battleResult?: BattleResponse;

  constructor(
    private speciesService: SpeciesService,
    private battleService: BattleService,
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
        console.error('Error al cargar las especies', error);
      },
    });
  }

  createBattle(): void {
    this.battleService.createBattle(this.battleRequest).subscribe({
      next: (response) => {
        this.battleResult = response;
        this.toastr.success(`Ganador: ${response.winner}`, 'Batalla finalizada');
        this.cdr.detectChanges();
      },

      error: (error) => {
        this.toastr.error(error.error?.message ?? 'Error al crear la batalla', 'Error');
      },
    });
  }

  startRandomBattle(): void {
    this.battleService.createRandomBattle().subscribe({
      next: (response) => {
        this.battleResult = response;
        this.toastr.success(`Ganador: ${response.winner}`, 'Batalla finalizada');
        this.cdr.detectChanges();
      },

      error: (error) => {
        this.toastr.error(error.error?.message ?? 'Error al crear la batalla', 'Error');
      },
    });
  }

  get selectedSpeciesOne(): Species | undefined {
    return this.speciesList.find((x) => x.id === Number(this.battleRequest.speciesOneId));
  }

  get selectedSpeciesTwo(): Species | undefined {
    return this.speciesList.find((x) => x.id === Number(this.battleRequest.speciesTwoId));
  }
}
