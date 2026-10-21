import type { LoginResponse } from "./auth";

const AUTH_STORAGE_KEY = "jobtex.auth";
const AUTH_CHANGE_EVENT = "jobtex-auth-change";

function announceAuthChange(): void {
  window.dispatchEvent(new Event(AUTH_CHANGE_EVENT));
}

export type AuthUser = {
  email: string;
  role: string;
  firstName?: string;
  lastName?: string;
};

export type AuthSession = LoginResponse & {
  user: AuthUser;
};

function getEmailFromAccessToken(accessToken: string): string {
  try {
    const payload = accessToken.split(".")[1];
    if (!payload) return "";
    const base64Payload = payload.replace(/-/g, "+").replace(/_/g, "/");
    const normalizedPayload = base64Payload.padEnd(
      base64Payload.length + ((4 - base64Payload.length % 4) % 4),
      "=",
    );
    const claims: unknown = JSON.parse(window.atob(normalizedPayload));
    return typeof claims === "object" && claims !== null && "sub" in claims
      && typeof claims.sub === "string" ? claims.sub : "";
  } catch {
    return "";
  }
}

export function saveAuthSession(session: LoginResponse, email: string, rememberMe: boolean): void {
  clearAuthSession();
  const storage = rememberMe ? window.localStorage : window.sessionStorage;
  const storedSession: AuthSession = {
    ...session,
    user: { email, role: session.role },
  };
  storage.setItem(AUTH_STORAGE_KEY, JSON.stringify(storedSession));
  announceAuthChange();
}

export function getAuthSession(): AuthSession | null {
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
      const authSession = session as LoginResponse & { user?: Partial<AuthUser> };
      const email = typeof authSession.user?.email === "string"
        ? authSession.user.email
        : getEmailFromAccessToken(authSession.accessToken);
      const role = typeof authSession.user?.role === "string"
        ? authSession.user.role
        : authSession.role;
      return {
        ...authSession,
        user: {
          email,
          role,
          firstName: authSession.user?.firstName,
          lastName: authSession.user?.lastName,
        },
      };
    }
  } catch {
    clearAuthSession();
  }

  return null;
}

export function saveAuthProfileName(firstName: string | null, lastName: string | null): void {
  const session = getAuthSession();
  if (!session) return;

  const storage = window.localStorage.getItem(AUTH_STORAGE_KEY) !== null
    ? window.localStorage
    : window.sessionStorage;
  storage.setItem(AUTH_STORAGE_KEY, JSON.stringify({
    ...session,
    user: {
      ...session.user,
      firstName: firstName?.trim() || undefined,
      lastName: lastName?.trim() || undefined,
    },
  }));
  announceAuthChange();
}

export function clearAuthSession(): void {
  window.localStorage.removeItem(AUTH_STORAGE_KEY);
  window.sessionStorage.removeItem(AUTH_STORAGE_KEY);
  announceAuthChange();
}

export { AUTH_CHANGE_EVENT };
