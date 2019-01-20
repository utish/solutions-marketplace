import { IProducer } from 'app/shared/model//producer.model';
import { IProblem } from 'app/shared/model//problem.model';

export interface ISolution {
    id?: number;
    name?: string;
    content?: string;
    rating?: string;
    producer?: IProducer;
    problem?: IProblem;
}

export class Solution implements ISolution {
    constructor(
        public id?: number,
        public name?: string,
        public content?: string,
        public rating?: string,
        public producer?: IProducer,
        public problem?: IProblem
    ) {}
}
