import type { BaseModel } from "./BaseModel";

export interface Author extends BaseModel {
  id: number;
  name: string;
  publishedBooks: number;
}
