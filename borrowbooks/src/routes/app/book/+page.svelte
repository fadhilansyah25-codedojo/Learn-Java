<script lang="ts">
  import Modal from "$lib/components/Modal/Modal.svelte";
  import type { Book } from "../../../Model/Book";
  import BookForm from "$lib/components/Form/BookForm.svelte";

  export let data: { books: Book[]; error: string | null };

  let headTitles: string[] = [
    "Title",
    "Author",
    "Category",
    "Publisher",
    "Stock",
    "Published Date",
    "Action",
  ];

  let isModalOpen = false;

  const openModal = () => {
    isModalOpen = true;
  };

  const handleModalClose = () => {
    isModalOpen = false;
  };
</script>

<section class="max-w-[85rem] mx-auto p-5">
  <!-- Table Section -->
  <div class="max-w-[85rem] px-4 py-10 sm:px-6 lg:px-8 lg:py-14 mx-auto">
    {#if data.error}
      <p class="error">Error: {data.error}</p>
    {/if}

    <!-- Card -->
    <div class="flex flex-col">
      <div class="-m-1.5 overflow-x-auto">
        <div class="p-1.5 min-w-full inline-block align-middle">
          <div
            class="bg-white border border-gray-200 rounded-xl shadow-sm overflow-hidden dark:bg-neutral-800 dark:border-neutral-700"
          >
            <!-- Header -->
            <div
              class="px-6 py-4 grid gap-3 md:flex md:justify-between md:items-center border-b border-gray-200 dark:border-neutral-700"
            >
              <div>
                <h2
                  class="text-xl font-semibold text-gray-800 dark:text-neutral-200"
                >
                  Books
                </h2>
                <p class="text-sm text-gray-600 dark:text-neutral-400">
                  Add books, edit and more.
                </p>
              </div>

              <div>
                <div class="inline-flex gap-x-2">
                  <a
                    class="py-2 px-3 inline-flex items-center gap-x-2 text-sm font-medium rounded-lg border border-gray-200 bg-white text-gray-800 shadow-sm hover:bg-gray-50 disabled:opacity-50 disabled:pointer-events-none focus:outline-none focus:bg-gray-50 dark:bg-transparent dark:border-neutral-700 dark:text-neutral-300 dark:hover:bg-neutral-800 dark:focus:bg-neutral-800"
                    href="/"
                  >
                    View all
                  </a>

                  <button
                    class="py-2 px-3 inline-flex items-center gap-x-2 text-sm font-medium rounded-lg border border-transparent bg-blue-600 text-white hover:bg-blue-700 focus:outline-none focus:bg-blue-700 disabled:opacity-50 disabled:pointer-events-none"
                    onclick={openModal}
                  >
                    <svg
                      class="shrink-0 size-4"
                      xmlns="http://www.w3.org/2000/svg"
                      width="24"
                      height="24"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      stroke-width="2"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      ><path d="M5 12h14" /><path d="M12 5v14" /></svg
                    >
                    Add Book
                  </button>
                </div>
              </div>
            </div>
            <!-- End Header -->

            <!-- Table -->
            <table
              class="min-w-full divide-y divide-gray-200 dark:divide-neutral-700"
            >
              <thead class="bg-gray-50 dark:bg-neutral-800">
                <tr>
                  <th scope="col" class="ps-6 py-3 text-start pe-5">
                    <label for="hs-at-with-checkboxes-main" class="flex">
                      <input
                        type="checkbox"
                        class="shrink-0 border-gray-300 rounded text-blue-600 focus:ring-blue-500 disabled:opacity-50 disabled:pointer-events-none dark:bg-neutral-800 dark:border-neutral-600 dark:checked:bg-blue-500 dark:checked:border-blue-500 dark:focus:ring-offset-gray-800"
                        id="hs-at-with-checkboxes-main"
                      />
                      <span class="sr-only">Checkbox</span>
                    </label>
                  </th>

                  {#each headTitles as headTitle}
                    <th
                      scope="col"
                      class="ps-6 lg:ps-3 xl:ps-0 pe-6 py-3 text-start"
                    >
                      <div class="flex items-center gap-x-2">
                        <span
                          class="text-xs font-semibold uppercase tracking-wide text-gray-800 dark:text-neutral-200"
                        >
                          {headTitle}
                        </span>
                      </div>
                    </th>
                  {/each}
                </tr>
              </thead>

              <tbody class="divide-y divide-gray-200 dark:divide-neutral-700">
                {#each data.books as book}
                  <tr>
                    <td class="size-px whitespace-nowrap">
                      <div class="ps-6 py-3">
                        <label for="hs-at-with-checkboxes-1" class="flex">
                          <input
                            type="checkbox"
                            class="shrink-0 border-gray-300 rounded text-blue-600 focus:ring-blue-500 disabled:opacity-50 disabled:pointer-events-none dark:bg-neutral-800 dark:border-neutral-600 dark:checked:bg-blue-500 dark:checked:border-blue-500 dark:focus:ring-offset-gray-800"
                            id="hs-at-with-checkboxes-1"
                          />
                          <span class="sr-only">Checkbox</span>
                        </label>
                      </div>
                    </td>
                    <td class="size-px whitespace-nowrap">
                      <div class="ps-6 lg:ps-3 xl:ps-0 pe-6 py-3">
                        <div class="flex items-center gap-x-3">
                          <div class="grow">
                            <span
                              class="block text-sm font-semibold text-gray-800 dark:text-neutral-200"
                              >{book.title}</span
                            >
                          </div>
                        </div>
                      </div>
                    </td>
                    <td class="size-px whitespace-nowrap">
                      <div class="ps-6 lg:ps-3 xl:ps-0 pe-6 py-3">
                        <div class="flex items-center gap-x-3">
                          <div class="grow">
                            <span
                              class="block text-sm font-medium text-gray-500 dark:text-neutral-200"
                              >{book.author.name}</span
                            >
                          </div>
                        </div>
                      </div>
                    </td>

                    <td class="size-px whitespace-nowrap">
                      <div class="ps-6 lg:ps-3 xl:ps-0 pe-6 py-3">
                        <div class="flex items-center gap-x-3">
                          <div class="grow">
                            <span
                              class="block text-sm text-gray-500 dark:text-neutral-200"
                              >{book.category.name}</span
                            >
                          </div>
                        </div>
                      </div>
                    </td>

                    <td class="size-px whitespace-nowrap">
                      <div class="ps-6 lg:ps-3 xl:ps-0 pe-6 py-3">
                        <div class="flex items-center gap-x-3">
                          <div class="grow">
                            <span
                              class="block text-sm text-gray-800 dark:text-neutral-200"
                              >{book.publisher.name}</span
                            >
                          </div>
                        </div>
                      </div>
                    </td>
                    <td class="size-px whitespace-nowrap">
                      <div class="ps-6 lg:ps-3 xl:ps-0 pe-6 py-3">
                        <div class="flex items-center gap-x-3">
                          <div class="grow">
                            <span
                              class="block text-sm font-semibold text-gray-800 dark:text-neutral-200"
                              >{book.stock}</span
                            >
                          </div>
                        </div>
                      </div>
                    </td>
                    <td class="size-px whitespace-nowrap">
                      <div class="px-6 py-3">
                        <span
                          class="text-sm text-gray-500 dark:text-neutral-500"
                          >{book.publishedAt}</span
                        >
                      </div>
                    </td>

                    <td class="size-px whitespace-nowrap">
                      <div class="px-6 py-1.5">
                        <a
                          class="inline-flex items-center gap-x-1 text-sm text-blue-600 decoration-2 hover:underline focus:outline-none focus:underline font-medium dark:text-blue-500"
                          href="/"
                        >
                          Edit
                        </a>
                      </div>
                    </td>
                  </tr>
                {/each}
              </tbody>
            </table>
            <!-- End Table -->

            <!-- Footer -->
            <!-- <div
              class="px-6 py-4 grid gap-3 md:flex md:justify-between md:items-center border-t border-gray-200 dark:border-neutral-700"
            >
              <div>
                <p class="text-sm text-gray-600 dark:text-neutral-400">
                  <span
                    class="font-semibold text-gray-800 dark:text-neutral-200"
                    >{data.books.length}</span
                  > results
                </p>
              </div>

              <div>
                <div class="inline-flex gap-x-2">
                  <button
                    type="button"
                    class="py-1.5 px-2 inline-flex items-center gap-x-2 text-sm font-medium rounded-lg border border-gray-200 bg-white text-gray-800 shadow-sm hover:bg-gray-50 disabled:opacity-50 disabled:pointer-events-none focus:outline-none focus:bg-gray-50 dark:bg-transparent dark:border-neutral-700 dark:text-neutral-300 dark:hover:bg-neutral-800 dark:focus:bg-neutral-800"
                  >
                    <svg
                      class="shrink-0 size-4"
                      xmlns="http://www.w3.org/2000/svg"
                      width="24"
                      height="24"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      stroke-width="2"
                      stroke-linecap="round"
                      stroke-linejoin="round"><path d="m15 18-6-6 6-6" /></svg
                    >
                    Prev
                  </button>

                  <button
                    type="button"
                    class="py-1.5 px-2 inline-flex items-center gap-x-2 text-sm font-medium rounded-lg border border-gray-200 bg-white text-gray-800 shadow-sm hover:bg-gray-50 disabled:opacity-50 disabled:pointer-events-none focus:outline-none focus:bg-gray-50 dark:bg-transparent dark:border-neutral-700 dark:text-neutral-300 dark:hover:bg-neutral-800 dark:focus:bg-neutral-800"
                  >
                    Next
                    <svg
                      class="shrink-0 size-4"
                      xmlns="http://www.w3.org/2000/svg"
                      width="24"
                      height="24"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      stroke-width="2"
                      stroke-linecap="round"
                      stroke-linejoin="round"><path d="m9 18 6-6-6-6" /></svg
                    >
                  </button>
                </div>
              </div>
            </div> -->
            <!-- End Footer -->
          </div>
        </div>
      </div>
    </div>
    <!-- End Card -->
  </div>
  <!-- End Table Section -->
</section>

<Modal bind:isOpen={isModalOpen} closeModal={handleModalClose} childComponent={BookForm} ></Modal>

