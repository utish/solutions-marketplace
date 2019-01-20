import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISolution } from 'app/shared/model/solution.model';

@Component({
    selector: 'jhi-solution-detail',
    templateUrl: './solution-detail.component.html'
})
export class SolutionDetailComponent implements OnInit {
    solution: ISolution;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ solution }) => {
            this.solution = solution;
        });
    }

    previousState() {
        window.history.back();
    }
}
