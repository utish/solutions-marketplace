import { IProducer } from 'app/shared/model//producer.model';
import { IConsumer } from 'app/shared/model//consumer.model';

export interface IMarketplace {
    id?: number;
    name?: string;
    producers?: IProducer[];
    consumers?: IConsumer[];
}

export class Marketplace implements IMarketplace {
    constructor(public id?: number, public name?: string, public producers?: IProducer[], public consumers?: IConsumer[]) {}
}
