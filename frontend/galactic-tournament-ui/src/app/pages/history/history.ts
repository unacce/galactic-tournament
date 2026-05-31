import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BattleService } from '../../core/services/battle.service';
import { BattleHistory } from '../../models/battle-history.model';
import { ChangeDetectorRef } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-history',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './history.html',
  styleUrl: './history.css',
})
export class HistoryComponent implements OnInit {
  historyList: BattleHistory[] = [];

  constructor(
    private battleService: BattleService,
    private cdr: ChangeDetectorRef,
    private toastr: ToastrService,
  ) {}

  ngOnInit(): void {
    this.loadHistory();
  }

  loadHistory(): void {
    this.battleService.getHistory().subscribe({
      next: (response) => {
        this.historyList = response;
        this.cdr.detectChanges();
      },

      error: (error) => {
        this.toastr.error(error.error?.message ?? 'Error al cargar el historial de batallas', 'Error');
      },
    });
  }
}
