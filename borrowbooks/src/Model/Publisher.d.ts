import type { BaseModel } from "./BaseModel";

export interface Publisher extends BaseModel {
    id: number;
    name: string;
    address: string;
}