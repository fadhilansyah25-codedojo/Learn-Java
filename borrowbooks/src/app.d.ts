// See https://svelte.dev/docs/kit/types#app.d.ts
// for information about these interfaces
declare global {
  namespace App {
    // interface Error {}
    // interface Locals {}
    // interface PageData {}
    // interface PageState {}
    // interface Platform {}
    interface PrivateEnv {
      // For server-side variables
      JWT_SECRET: string;
    }
    interface PublicEnv {
      // For client-side variables
      PUBLIC_API_URL: string;
    }
  }
}

export { __global };
