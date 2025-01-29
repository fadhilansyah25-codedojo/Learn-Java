// import { redirect } from "@sveltejs/kit";
// import jwt from "jsonwebtoken";
// import {JWT_SECRET} from "$env/static/private"

// export const load = async ({ cookies }) => {
//   const token = cookies.get("jwt") as string;

//   try {
//     jwt.verify(token, JWT_SECRET);
//   } catch (err) {
//     if (err instanceof Error) {
//       console.error(err);
//     }
//     throw redirect(302, "/login");
//   }
// };

import { redirect } from "@sveltejs/kit";
import { PUBLIC_API_URL } from "$env/static/public";

export async function load({
  fetch,
  // cookies
}) {
  // Ambil JWT dari cookie
  // const jwt = cookies.get("jwt") as string;

  // console.log(jwt);

  // Jika tidak ada JWT, redirect ke login
  // if (!jwt) {
  //   throw redirect(302, "/login");
  // }

  // Validasi JWT dengan backend
  try {
    const response = await fetch(`${PUBLIC_API_URL}/api/validate`, {
      method: "POST",
      // headers: {
      //   Cookie: `jwt=${jwt}`, // Kirim cookie ke backend
      // },
      credentials: "include",
    });

    // console.log(response);

    // Jika tidak valid, redirect ke login
    if (!response.ok) {
      throw redirect(302, "/login");
    }
  } catch (error) {
    throw redirect(302, "/login");
  }

  // Jika valid, lanjutkan
  return {};
}
