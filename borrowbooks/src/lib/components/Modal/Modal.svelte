<script lang="ts">
  import { fade } from "svelte/transition";

  //   let { isOpen, onClose }: { isOpen: boolean; onClose: () => void } = $props();

  export let isOpen = false;

  export const closeModal = () => {
    isOpen = false; // Notify parent via binding
  };

  export let childComponent;

  const handleOverlayClick = (event: { target: any; currentTarget: any }) => {
    if (event.target === event.currentTarget) {
      closeModal(); // Close when clicking outside the modal content
    }
  };
</script>

<!-- svelte-ignore a11y_click_events_have_key_events -->
<!-- svelte-ignore a11y_no_noninteractive_element_interactions -->
<div
  in:fade={{ duration: 100 }}
  out:fade={{ duration: 100 }}
  id="hs-basic-modal"
  class="{isOpen
    ? 'opacity-100'
    : 'opacity-0 hidden'} transition-opacity ease-in-out delay-150 duration-300 fixed top-0 left-0 z-[80] w-full h-full bg-black bg-opacity-50 flex justify-center items-center overflow-y-auto"
  role="dialog"
  aria-labelledby="hs-basic-modal-label"
  tabindex="-1"
  onclick={handleOverlayClick}
>
  <div class="sm:max-w-lg sm:w-full m-3 sm:mx-auto">
    <div
      class="flex flex-col bg-white border shadow-sm rounded-xl pointer-events-auto dark:bg-neutral-800 dark:border-neutral-700 dark:shadow-neutral-700/70"
    >
      <!-- Header -->
      <div
        class="flex justify-between items-center py-3 px-4 border-b dark:border-neutral-700"
      >
        <h3
          id="hs-basic-modal-label"
          class="font-bold text-gray-800 dark:text-white"
        >
          Modal title
        </h3>
        <button
          type="button"
          class="size-8 inline-flex justify-center items-center gap-x-2 rounded-full border bg-gray-100 hover:bg-gray-200 dark:bg-neutral-700 dark:hover:bg-neutral-600"
          aria-label="Close"
          onclick={closeModal}
        >
          <span class="sr-only">Close</span>
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
          >
            <path d="M18 6 6 18"></path>
            <path d="M6 6l12 12"></path>
          </svg>
        </button>
      </div>
      <!-- Body -->
      <div class="p-4 overflow-y-auto">
        <svelte:component this={childComponent} />
      </div>
      <!-- Footer -->
      <!-- <div
        class="flex justify-end items-center gap-x-2 py-3 px-4 border-t dark:border-neutral-700"
      >
        <button
          type="button"
          class="py-2 px-3 inline-flex items-center gap-x-2 text-sm font-medium rounded-lg border bg-white hover:bg-gray-50 dark:bg-neutral-800 dark:hover:bg-neutral-700"
          onclick={closeModal}
        >
          Close
        </button>
        <button
          type="button"
          class="py-2 px-3 inline-flex items-center gap-x-2 text-sm font-medium rounded-lg bg-blue-600 text-white hover:bg-blue-700"
        >
          Save changes
        </button>
      </div> -->
    </div>
  </div>
</div>
