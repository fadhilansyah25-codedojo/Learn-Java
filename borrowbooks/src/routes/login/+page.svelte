<script>
  import { PUBLIC_API_URL } from "$env/static/public";

  let username = "";
  let password = "";
  let error = "";

  async function handleLogin() {
    try {
      if (!username || !password) {
        error = "Username dan password wajib diisi!";
        return;
      }

      const response = await fetch(`${PUBLIC_API_URL}/api/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
        credentials: 'include' // Penting untuk mengirim/menerima cookie
      });

      if (response.ok) {
        window.location.href = "/app/book";
      } else {
        error = "Login gagal! Periksa username dan password Anda.";
      }
    } catch (err) {
      if (err instanceof Error) {
        console.log(err.message);
      }
    }
  }
</script>

<form on:submit|preventDefault={handleLogin}>
  <input type="text" bind:value={username} placeholder="Username" required />
  <input
    type="password"
    bind:value={password}
    placeholder="Password"
    required
  />
  <button type="submit">Login</button>
  {#if error}<p style="color: red;">{error}</p>{/if}
</form>
