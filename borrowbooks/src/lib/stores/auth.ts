import { writable } from "svelte/store";

export const auth = writable<{ token: string | null }>({ token: null });
