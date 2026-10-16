import { createBrowserRouter } from "react-router";
import App from "../../App";
import HomePage from "../features/home/HomePage";
import LoginPage from "../features/auth/LoginPage";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      { path: "", element: <HomePage /> },
      { path: "/auth/login", element: <LoginPage /> },
    ],
  },
]);