import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SpeciesService } from '../../core/services/species.service';
import { Ranking } from '../../models/ranking.model';
import { ChangeDetectorRef } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-ranking',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './ranking.html',
  styleUrl: './ranking.css',
})
export class RankingComponent implements OnInit {
  rankingList: Ranking[] = [];

  constructor(
    private speciesService: SpeciesService,
    private cdr: ChangeDetectorRef,
    private toastr: ToastrService,
  ) {}

  ngOnInit(): void {
    this.loadRanking();
  }

  loadRanking(): void {
    this.speciesService.getRanking().subscribe({
      next: (response) => {
        this.rankingList = response;
        this.cdr.detectChanges();
      },

      error: (error) => {
        this.toastr.error(error.error?.message ?? 'Error al cargar la clasificación', 'Error');
      },
    });
  }

  get hasRanking(): boolean {

  return this.rankingList.some(
    x => x.victories > 1
  );

}
}
