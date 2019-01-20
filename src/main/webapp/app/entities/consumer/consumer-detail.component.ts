import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConsumer } from 'app/shared/model/consumer.model';

@Component({
    selector: 'jhi-consumer-detail',
    templateUrl: './consumer-detail.component.html'
})
export class ConsumerDetailComponent implements OnInit {
    consumer: IConsumer;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ consumer }) => {
            this.consumer = consumer;
        });
    }

    previousState() {
        window.history.back();
    }
}
