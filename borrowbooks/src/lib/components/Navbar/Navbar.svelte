<script lang="ts">
  let navLinks: { title: string; path: string }[] = [
    {
      title: "Books",
      path: "/app/book",
    },
    {
      title: "Categories",
      path: "/app/category",
    },
    {
      title: "Authors",
      path: "/app/author",
    },
    {
      title: "Publishers",
      path: "/app/publisher",
    },
    {
      title: "Users",
      path: "/app/user",
    },
  ];
</script>

<header
  class="flex flex-wrap sm:justify-start sm:flex-nowrap w-full bg-white text-sm py-3 dark:bg-neutral-800"
>
  <nav
    class="max-w-[85rem] w-full mx-auto px-4 sm:flex sm:items-center sm:justify-between"
  >
    <a
      class="flex-none font-semibold text-xl text-black focus:outline-none focus:opacity-80 dark:text-white"
      href="/"
      aria-label="Brand">BorrowBooks</a
    >
    <div
      class="flex flex-row items-center gap-5 mt-5 sm:justify-end sm:mt-0 sm:ps-5"
    >
      <!-- <a
        class="font-medium text-blue-500 focus:outline-none"
        href="#"
        aria-current="page">Landing</a
      > -->
      {#each navLinks as navLink, i}
        <a
          class="font-medium text-gray-600 hover:text-gray-400 focus:outline-none focus:text-gray-400 dark:text-neutral-400 dark:hover:text-neutral-500 dark:focus:text-neutral-500"
          href={navLink.path}>{navLink.title}</a
        >
      {/each}
    </div>
  </nav>
</header>
