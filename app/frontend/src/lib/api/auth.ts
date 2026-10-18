import { agent } from "./agent";

export type LoginRequest = {
  email: string;
  password: string;
};

export type LoginResponse = {
  accessToken: string;
  refreshToken: string;
  role: string;
};

export async function login(request: LoginRequest): Promise<LoginResponse> {
  const response = await agent.post<LoginResponse>("/auth/login", request);
  return response.data;
}

export type RegisterRequest = {
  email: string;
  password: string;
  confirmPassword: string;
  userTypeEnum: "JOB_SEEKER";
  provider: "LOCAL";
};

export async function register(request: RegisterRequest): Promise<void> {
  await agent.post("/auth/register", request);
}

export async function verifyEmailOtp(email: string, otpCode: string): Promise<void> {
  await agent.post("/auth/verify-otp", { email, otpCode });
}

export async function resendEmailOtp(email: string): Promise<void> {
  await agent.post("/auth/resend-otp", { email });
}
