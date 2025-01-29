import type { Author } from "./Author";
import type { BaseModel } from "./BaseModel";
import type { Category } from "./Category";
import type { Publisher } from "./Publisher";

export interface Book extends BaseModel {
  id: number;
  author: Author;
  category: Category;
  publisher: Publisher;
  title: string;
  synopsis: string;
  stock: number;
  publishedAt: Date;
}
