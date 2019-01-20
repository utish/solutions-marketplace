import { IConsumer } from 'app/shared/model//consumer.model';
import { ISolution } from 'app/shared/model//solution.model';

export interface IProblem {
    id?: number;
    name?: string;
    statement?: string;
    consumer?: IConsumer;
    solutions?: ISolution[];
}

export class Problem implements IProblem {
    constructor(
        public id?: number,
        public name?: string,
        public statement?: string,
        public consumer?: IConsumer,
        public solutions?: ISolution[]
    ) {}
}
