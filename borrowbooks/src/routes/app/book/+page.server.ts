import { error } from "@sveltejs/kit";
import type { ApiResponse } from "../../../Model/ApiResponse";
import type { Book } from "../../../Model/Book.js";
import { PUBLIC_API_URL } from "$env/static/public";

export const load = async ({ fetch }) => {
  try {
    const response = await fetch(`${PUBLIC_API_URL}/api/book`, {
      credentials: "include",
    });

    if (!response.ok) {
      throw new Error(`Failed to fetch: ${response.status}`);
    }

    const result: ApiResponse<Book[]> = await response.json();

    if (+result.status !== 200) {
      throw error(+result.status, result.message);
    }

    return {
      books: result.data,
      error: null,
    };
  } catch (err) {
    console.error(err);
    return {
      books: [],
      error: err instanceof Error ? err.message : "Unknown error",
    };
  }
};
