import { IMarketplace } from 'app/shared/model//marketplace.model';
import { IProblem } from 'app/shared/model//problem.model';

export interface IConsumer {
    id?: number;
    name?: string;
    email?: string;
    marketplace?: IMarketplace;
    problems?: IProblem[];
}

export class Consumer implements IConsumer {
    constructor(
        public id?: number,
        public name?: string,
        public email?: string,
        public marketplace?: IMarketplace,
        public problems?: IProblem[]
    ) {}
}
