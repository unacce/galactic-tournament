import { Routes } from '@angular/router';
import { SpeciesComponent } from './pages/species/species';
import { BattleComponent } from './pages/battle/battle';
import { RankingComponent } from './pages/ranking/ranking';
import { HistoryComponent } from './pages/history/history';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'species',
    pathMatch: 'full',
  },

  {
    path: 'species',
    component: SpeciesComponent,
  },

  {
    path: 'battle',
    component: BattleComponent,
  },

  {
    path: 'ranking',
    component: RankingComponent,
  },

  {
    path: 'history',
    component: HistoryComponent,
  },
];
