import axios from "axios";
import { getAuthSession } from "./authStorage";

const baseURL = import.meta.env.VITE_API_URL || "http://localhost:8080/api";

export const agent = axios.create({
  baseURL: baseURL.replace(/\/$/, ""),
  headers: {
    "Content-Type": "application/json",
  },
});

agent.interceptors.request.use((config) => {
  const accessToken = getAuthSession()?.accessToken;
  if (accessToken) {
    config.headers.Authorization = `Bearer ${accessToken}`;
  }
  return config;
});

type ApiErrorResponse = {
  code?: string;
  messages?: Record<string, string>;
};

export function getApiErrorMessage(error: unknown, fallback: string): string {
  if (axios.isAxiosError<ApiErrorResponse>(error)) {
    const messages = error.response?.data?.messages;
    const firstMessage = messages && Object.values(messages).find(Boolean);
    if (firstMessage) return firstMessage;
    if (error.code === "ERR_NETWORK") {
      return "Cannot connect to the server. Check that the backend is running and try again.";
    }
  }

  return fallback;
}
