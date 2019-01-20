import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISolution } from 'app/shared/model/solution.model';
import { SolutionService } from './solution.service';
import { IProducer } from 'app/shared/model/producer.model';
import { ProducerService } from 'app/entities/producer';
import { IProblem } from 'app/shared/model/problem.model';
import { ProblemService } from 'app/entities/problem';

@Component({
    selector: 'jhi-solution-update',
    templateUrl: './solution-update.component.html'
})
export class SolutionUpdateComponent implements OnInit {
    solution: ISolution;
    isSaving: boolean;

    producers: IProducer[];

    problems: IProblem[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected solutionService: SolutionService,
        protected producerService: ProducerService,
        protected problemService: ProblemService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ solution }) => {
            this.solution = solution;
        });
        this.producerService.query().subscribe(
            (res: HttpResponse<IProducer[]>) => {
                this.producers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.problemService.query().subscribe(
            (res: HttpResponse<IProblem[]>) => {
                this.problems = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.solution.id !== undefined) {
            this.subscribeToSaveResponse(this.solutionService.update(this.solution));
        } else {
            this.subscribeToSaveResponse(this.solutionService.create(this.solution));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISolution>>) {
        result.subscribe((res: HttpResponse<ISolution>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackProducerById(index: number, item: IProducer) {
        return item.id;
    }

    trackProblemById(index: number, item: IProblem) {
        return item.id;
    }
}
