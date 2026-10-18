import type { LoginResponse } from "./auth";

const AUTH_STORAGE_KEY = "jobtex.auth";

export function saveAuthSession(session: LoginResponse, rememberMe: boolean): void {
  clearAuthSession();
  const storage = rememberMe ? window.localStorage : window.sessionStorage;
  storage.setItem(AUTH_STORAGE_KEY, JSON.stringify(session));
}

export function getAuthSession(): LoginResponse | null {
  const storedSession = window.localStorage.getItem(AUTH_STORAGE_KEY)
    ?? window.sessionStorage.getItem(AUTH_STORAGE_KEY);

  if (!storedSession) return null;

  try {
    const session: unknown = JSON.parse(storedSession);
    if (
      typeof session === "object" && session !== null
      && "accessToken" in session && typeof session.accessToken === "string"
      && "refreshToken" in session && typeof session.refreshToken === "string"
      && "role" in session && typeof session.role === "string"
    ) {
      return session as LoginResponse;
    }
  } catch {
    clearAuthSession();
  }

  return null;
}

export function clearAuthSession(): void {
  window.localStorage.removeItem(AUTH_STORAGE_KEY);
  window.sessionStorage.removeItem(AUTH_STORAGE_KEY);
}
