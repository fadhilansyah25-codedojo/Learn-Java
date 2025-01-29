<script lang="ts">
  import type { Author } from "../../../Model/Author";
  import { onMount } from "svelte";
  import type { ApiResponse } from "../../../Model/ApiResponse";
  import type { Publisher } from "../../../Model/Publisher";
  import type { Category } from "../../../Model/Category";
  import { PUBLIC_API_URL } from "$env/static/public";

  export let authors: Author[];
  export let publishers: Publisher[];
  export let categories: Category[];

  let formData = {
    id: "",
    title: "",
    stock: 0,
    synopsis: "",
    publishedAt: "",
    author_id: "0",
    category_id: "0",
    publisher_id: "0",
  };

  onMount(async () => {
    try {
      const author = await fetch(`${PUBLIC_API_URL}/api/author`, {
        credentials: "include",
      });
      const category = await fetch(`${PUBLIC_API_URL}/api/category`, {
        credentials: "include",
      });
      const publisher = await fetch(`${PUBLIC_API_URL}/api/publisher`, {
        credentials: "include",
      });

      authors = ((await author.json()) as ApiResponse<Author[]>).data;
      publishers = ((await category.json()) as ApiResponse<Publisher[]>).data;
      categories = ((await publisher.json()) as ApiResponse<Category[]>).data;
    } catch (err) {
      if (err instanceof Error) {
        console.log(err.message);
      }
    }
  });

  async function saveBook(event: Event) {
    event.preventDefault(); // Prevent default form submission behavior

    try {
      const response = await fetch(`${PUBLIC_API_URL}/api/book`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
        credentials: "include"
      });

      if (!response.ok) {
        throw new Error(`Failed to save book: ${response.statusText}`);
      }

      const result = await response.json();
      console.log("Book saved successfully:", result);
      // Reset form data or redirect to another page, if necessary
      formData = {
        id: "",
        title: "",
        stock: 0,
        synopsis: "",
        publishedAt: "",
        author_id: "",
        category_id: "",
        publisher_id: "",
      };
      window.location.reload(); // Refresh the page
    } catch (err) {
      console.error(err instanceof Error ? err.message : "Unknown error");
    }
  }
</script>

<form on:submit={saveBook}>
  <input
    hidden
    type="text"
    id="id"
    class="border py-3 px-4 w-full border-gray-200 rounded-lg text-sm focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50 disabled:pointer-events-none dark:bg-neutral-900 dark:border-neutral-700 dark:text-neutral-400 dark:placeholder-neutral-500 dark:focus:ring-neutral-600"
    name="id"
    bind:value={formData.title}
  />

  <div class="mb-3">
    <label for="title" class="block text-sm font-medium mb-2 dark:text-white"
      >Title</label
    >
    <input
      type="text"
      id="title"
      class="border py-3 px-4 block w-full border-gray-200 rounded-lg text-sm focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50 disabled:pointer-events-none dark:bg-neutral-900 dark:border-neutral-700 dark:text-neutral-400 dark:placeholder-neutral-500 dark:focus:ring-neutral-600"
      placeholder="Input book title"
      name="title"
      bind:value={formData.title}
    />
  </div>
  <div class="mb-3">
    <label for="stock" class="block text-sm font-medium mb-2 dark:text-white"
      >Stock</label
    >
    <input
      type="number"
      id="stock"
      class="border py-3 px-4 block w-full border-gray-200 rounded-lg text-sm focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50 disabled:pointer-events-none dark:bg-neutral-900 dark:border-neutral-700 dark:text-neutral-400 dark:placeholder-neutral-500 dark:focus:ring-neutral-600"
      placeholder="Input book stock"
      name="stock"
      min="0"
      bind:value={formData.stock}
    />
  </div>
  <div class="mb-3">
    <label for="synopsis" class="block text-sm font-medium mb-2 dark:text-white"
      >Synopsis</label
    >
    <textarea
      id="synopsis"
      class="border py-3 px-4 block w-full border-gray-200 rounded-lg text-sm focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50 disabled:pointer-events-none dark:bg-neutral-900 dark:border-neutral-700 dark:text-neutral-400 dark:placeholder-neutral-500 dark:focus:ring-neutral-600"
      rows="3"
      placeholder="Write sysnopsis..."
      name="synopsis"
      bind:value={formData.synopsis}
    ></textarea>
  </div>
  <div class="mb-3">
    <label
      for="publishedAt"
      class="block text-sm font-medium mb-2 dark:text-white"
      >Published Date</label
    >
    <input
      type="date"
      id="publishedAt"
      class="border py-3 px-4 block w-full border-gray-200 rounded-lg text-sm focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50 disabled:pointer-events-none dark:bg-neutral-900 dark:border-neutral-700 dark:text-neutral-400 dark:placeholder-neutral-500 dark:focus:ring-neutral-600"
      name="publishedAt"
      bind:value={formData.publishedAt}
    />
  </div>
  <div class="mb-3">
    <label
      for="author_id"
      class="block text-sm font-medium mb-2 dark:text-white">Author</label
    >
    <select
      class="border py-3 px-4 pe-9 block w-full border-gray-200 rounded-lg text-sm focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50 disabled:pointer-events-none dark:bg-neutral-900 dark:border-neutral-700 dark:text-neutral-400 dark:placeholder-neutral-500 dark:focus:ring-neutral-600"
      name="author_id"
      id="author_id"
      bind:value={formData.author_id}
    >
      <option value="0" selected disabled>Select Author</option>
      {#if authors != null}
        {#each authors as item}
          <option value={item.id}>{item.name}</option>
        {/each}
      {/if}
    </select>
  </div>
  <div class="mb-3">
    <label
      for="category_id"
      class="block text-sm font-medium mb-2 dark:text-white">Category</label
    >
    <select
      class="border py-3 px-4 pe-9 block w-full border-gray-200 rounded-lg text-sm focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50 disabled:pointer-events-none dark:bg-neutral-900 dark:border-neutral-700 dark:text-neutral-400 dark:placeholder-neutral-500 dark:focus:ring-neutral-600"
      name="category_id"
      id="category_id"
      bind:value={formData.category_id}
    >
      <option value="0" selected disabled>Select Category</option>
      {#if categories != null}
        {#each categories as item}
          <option value={item.id}>{item.name}</option>
        {/each}
      {/if}
    </select>
  </div>
  <div class="mb-3">
    <label
      for="publisher_id"
      class="block text-sm font-medium mb-2 dark:text-white">Author</label
    >
    <select
      class="border py-3 px-4 pe-9 block w-full border-gray-200 rounded-lg text-sm focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50 disabled:pointer-events-none dark:bg-neutral-900 dark:border-neutral-700 dark:text-neutral-400 dark:placeholder-neutral-500 dark:focus:ring-neutral-600"
      name="publisher_id"
      id="publisher_id"
      bind:value={formData.publisher_id}
    >
      <option value="0" selected disabled>Select Publisher</option>
      {#if publishers != null}
        {#each publishers as item}
          <option value={item.id}>{item.name}</option>
        {/each}
      {/if}
    </select>
  </div>

  <div
    class="mt-10 flex justify-end items-center gap-x-2 py-3 px-4 border-t dark:border-neutral-700"
  >
    <button
      type="submit"
      class="py-2 px-3 inline-flex items-center gap-x-2 text-sm font-medium rounded-lg bg-blue-600 text-white hover:bg-blue-700"
    >
      Save changes
    </button>
  </div>
</form>
