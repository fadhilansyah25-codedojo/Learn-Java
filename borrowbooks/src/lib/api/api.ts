// // src/lib/api.ts
// import type { ApiResponse } from "../../Model/ApiResponse";

// export async function fetchData<T>(
//   apiEndpoint: string
// ): Promise<ApiResponse<T> {
//   try {
//     const response = await fetch(apiEndpoint);
//     if (!response.ok) {
//       throw new Error(`Failed to fetch: ${response.status}`);
//     }

//     const result: ApiResponse<T> = await response.json();
//     if (+result.status !== 200) {
//       throw new Error(result.message);
//     }

//     return result
//   } catch (err) {
//     if(err instanceof Error) {
//         console.error(err.message)
//     }
//   }
// }
