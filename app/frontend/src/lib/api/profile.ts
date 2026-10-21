import { agent } from "./agent";

export type ProfileName = {
  firstName: string | null;
  lastName: string | null;
};

export async function getMyProfileName(role: string): Promise<ProfileName> {
  const normalizedRole = role.toUpperCase();
  const endpoint = normalizedRole === "JOB_SEEKER"
    ? "/job-seekers/me"
    : normalizedRole === "RECRUITER"
      ? "/users/recruiter/my-profile"
      : normalizedRole === "ADMIN"
        ? "/users/admin/my-profile"
        : null;

  if (!endpoint) return { firstName: null, lastName: null };

  const response = await agent.get<Partial<ProfileName>>(endpoint);
  return {
    firstName: response.data.firstName ?? null,
    lastName: response.data.lastName ?? null,
  };
}
