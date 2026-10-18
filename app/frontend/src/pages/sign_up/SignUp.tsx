import { useEffect, useRef, useState, type SubmitEvent, type ClipboardEvent, type KeyboardEvent } from "react";
import "../auth/Auth.css";
import TrustIcons from "../../components/layout/ui/TrustIcons";
import Trust from "../../components/layout/ui/Trust";
import FadeUp from "../../components/layout/ui/FadeUp";
import AuthHero from "../../components/layout/AuthHero";
import JobNotifications from "../../components/layout/JobNotifications";

type SignUpProps = {
  /** Connect this callback to the registration API in the parent application. */
  onSubmitRegistration?: (data: { email: string; password: string }) => void | Promise<void>;
  /** Optional handler for the existing sign-in flow. */
  onSignInClick?: () => void;
  /** Optional handler for opening Terms of Service. */
  onTermsClick?: () => void;
  /** Optional handler for opening the Privacy Policy. */
  onPrivacyClick?: () => void;
};

const OTP_LENGTH = 6;

function GoogleIcon() {
  return (
    <svg className="jobtex-signup__google-icon" viewBox="0 0 24 24" aria-hidden="true">
      <path fill="#4285F4" d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z" />
      <path fill="#34A853" d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z" />
      <path fill="#FBBC05" d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.06H2.18A11 11 0 0 0 1 12c0 1.78.43 3.45 1.18 4.94l2.85-2.22.81-.63z" />
      <path fill="#EA4335" d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.06l3.66 2.84c.87-2.6 3.3-4.52 6.16-4.52z" />
    </svg>
  );
}

export default function SignUp({
  onSubmitRegistration,
  onSignInClick,
  onTermsClick,
  onPrivacyClick,
}: SignUpProps) {
  const [step, setStep] = useState<"signup" | "verify">("signup");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [termsAccepted, setTermsAccepted] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const [error, setError] = useState("");
  const [otp, setOtp] = useState<string[]>(Array(OTP_LENGTH).fill(""));
  const [otpMessage, setOtpMessage] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [resendSeconds, setResendSeconds] = useState(45);
  const otpRefs = useRef<Array<HTMLInputElement | null>>([]);

  useEffect(() => {
    if (step !== "verify" || resendSeconds <= 0) return;
    const timer = window.setTimeout(() => setResendSeconds((seconds) => Math.max(0, seconds - 1)), 1000);
    return () => window.clearTimeout(timer);
  }, [step, resendSeconds]);

  const handleSignUp = async (event: SubmitEvent) => {
    event.preventDefault();
    setError("");

    // if (!/^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$/.test(email.trim())) {
    //   setError("Please enter a valid email address.");
    //   return;
    // }
    if (password.length < 8) {
      setError("Password must contain at least 8 characters.");
      return;
    }
    if (password !== confirmPassword) {
      setError("Passwords do not match.");
      return;
    }
    if (!termsAccepted) {
      setError("Please agree to the Terms of Service and Privacy Policy.");
      return;
    }

    if (onSubmitRegistration) {
      setIsSubmitting(true);
      try {
        await onSubmitRegistration({ email: email.trim(), password });
        setOtpMessage("Registration request submitted. Connect your verification service to send an email code.");
      } catch {
        setError("We couldn't submit your registration. Please try again.");
        setIsSubmitting(false);
        return;
      }
      setIsSubmitting(false);
    } else {
      setOtpMessage("UI preview only: connect your registration API to send a verification code.");
    }

    setStep("verify");
    setResendSeconds(45);
    window.setTimeout(() => otpRefs.current[0]?.focus(), 0);
  };

  const handleOtpChange = (index: number, value: string) => {
    const digit = value.replace(/\D/g, "").slice(-1);
    setOtp((current) => current.map((item, i) => (i === index ? digit : item)));
    if (digit && index < OTP_LENGTH - 1) otpRefs.current[index + 1]?.focus();
  };

  const handleOtpKeyDown = (index: number, event: KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Backspace" && !otp[index] && index > 0) otpRefs.current[index - 1]?.focus();
    if (event.key === "ArrowLeft" && index > 0) otpRefs.current[index - 1]?.focus();
    if (event.key === "ArrowRight" && index < OTP_LENGTH - 1) otpRefs.current[index + 1]?.focus();
  };

  const handleOtpPaste = (event: ClipboardEvent<HTMLInputElement>) => {
    const pasted = event.clipboardData.getData("text").replace(/\D/g, "").slice(0, OTP_LENGTH);
    if (!pasted) return;
    event.preventDefault();
    setOtp(Array.from({ length: OTP_LENGTH }, (_, index) => pasted[index] ?? ""));
    otpRefs.current[Math.min(pasted.length, OTP_LENGTH) - 1]?.focus();
  };

  const handleResend = () => {
    // This is intentionally UI-only; connect an OTP resend API before production use.
    setResendSeconds(45);
    setOtpMessage("UI preview only: connect your verification service to resend the code.");
  };

  const handleVerify = (event: SubmitEvent) => {
    event.preventDefault();
    setOtpMessage("Verification UI is ready. Connect your OTP verification API to complete registration.");
  };

  return (
    <>
    <AuthHero title="Sign Up" />
    <section className="auth-page jobtex-signup" aria-labelledby="jobtex-signup-title">
      <div className="jobtex-signup__ambient jobtex-signup__ambient--one" aria-hidden="true" />
      <div className="jobtex-signup__ambient jobtex-signup__ambient--two" aria-hidden="true" />

      <FadeUp className="auth-shell jobtex-signup__shell">
        <div className="auth-card jobtex-signup__card">
          {step === "signup" ? (
            <div className="jobtex-signup__panel">
              <header className="auth-header jobtex-signup__header">
                <h1 id="jobtex-signup-title">Sign Up</h1>
                <p>Create your Jobtex account to access top career opportunities and talent profiles.</p>
              </header>

              <form className="jobtex-signup__form" onSubmit={handleSignUp} noValidate>
                <div className="jobtex-signup__field">
                  <label htmlFor="jobtex-signup-email">Email Address</label>
                  <div className="jobtex-signup__input-wrap">
                    <TrustIcons name="mail" />
                    <input
                      id="jobtex-signup-email"
                      type="email"
                      autoComplete="email"
                      placeholder="Enter Your Email"
                      value={email}
                      onChange={(event) => setEmail(event.target.value)}
                      required
                    />
                  </div>
                </div>

                <div className="jobtex-signup__field">
                  <label htmlFor="jobtex-signup-password">Password</label>
                  <div className="jobtex-signup__input-wrap">
                    <TrustIcons name="lock" />
                    <input
                      id="jobtex-signup-password"
                      type={showPassword ? "text" : "password"}
                      autoComplete="new-password"
                      placeholder="Create a strong password (min. 8 chars)"
                      value={password}
                      onChange={(event) => setPassword(event.target.value)}
                      minLength={8}
                      required
                    />
                    <button
                      className="jobtex-signup__visibility"
                      type="button"
                      aria-label={showPassword ? "Hide password" : "Show password"}
                      aria-pressed={showPassword}
                      onClick={() => setShowPassword((shown) => !shown)}
                    >
                      <TrustIcons name={showPassword ? "visibility_off" : "visibility"} />
                    </button>
                  </div>
                </div>

                <div className="jobtex-signup__field">
                  <label htmlFor="jobtex-signup-confirm-password">Confirm Password</label>
                  <div className="jobtex-signup__input-wrap">
                    <TrustIcons name="lock_reset" />
                    <input
                      id="jobtex-signup-confirm-password"
                      type={showConfirmPassword ? "text" : "password"}
                      autoComplete="new-password"
                      placeholder="Re-enter your password"
                      value={confirmPassword}
                      onChange={(event) => setConfirmPassword(event.target.value)}
                      required
                    />
                    <button
                      className="jobtex-signup__visibility"
                      type="button"
                      aria-label={showConfirmPassword ? "Hide confirm password" : "Show confirm password"}
                      aria-pressed={showConfirmPassword}
                      onClick={() => setShowConfirmPassword((shown) => !shown)}
                    >
                      <TrustIcons name={showConfirmPassword ? "visibility_off" : "visibility"} />
                    </button>
                  </div>
                </div>

                <div className="jobtex-signup__terms">
                  <input
                    id="jobtex-signup-terms"
                    type="checkbox"
                    checked={termsAccepted}
                    onChange={(event) => setTermsAccepted(event.target.checked)}
                    required
                  />
                  <label htmlFor="jobtex-signup-terms">
                    I agree to the jobtex{" "}
                    <a href="/terms" onClick={onTermsClick}>Terms of Service</a>
                    {" & "}
                    <a href="/privacy" onClick={onPrivacyClick}>Privacy Policy</a>.
                  </label>
                </div>

                {error && <p className="jobtex-signup__error" role="alert">{error}</p>}

                <button className="jobtex-signup__submit" type="submit" disabled={isSubmitting}>
                  <span>{isSubmitting ? "Submitting..." : "Sign Up & Verify"}</span>
                  <TrustIcons name="arrow_forward" />
                </button>
              </form>

              <div className="jobtex-signup__divider"><span>Or continue with</span></div>

              <button
                className="jobtex-signup__google"
                type="button"
                onClick={() => setError("Google sign-up is not connected yet.")}
              >
                <GoogleIcon />
                <span>Continue with Google</span>
              </button>

              <div className="jobtex-signup__signin">
                <p>Already have an account?{" "}
                  <a href="/login" onClick={onSignInClick}>Sign in »</a>
                </p>
              </div>
            </div>
          ) : (
            <div className="jobtex-signup__panel jobtex-signup__verification">
              <header className="jobtex-signup__verify-header">
                <div className="jobtex-signup__verify-icon"><TrustIcons name="mark_email_read" /></div>
                <h2>Verify Your Email</h2>
                <p>Enter the 6-digit verification code for <strong>{email}</strong>.</p>
              </header>

              <form className="jobtex-signup__otp-form" onSubmit={handleVerify}>
                <div className="jobtex-signup__otp-row" aria-label="6-digit verification code">
                  {otp.map((digit, index) => (
                    <div className="jobtex-signup__otp-cell" key={index}>
                      {index === 3 && <span className="jobtex-signup__otp-dash" aria-hidden="true">-</span>}
                      <input
                        ref={(element) => { otpRefs.current[index] = element; }}
                        aria-label={`Verification digit ${index + 1}`}
                        autoComplete={index === 0 ? "one-time-code" : "off"}
                        inputMode="numeric"
                        pattern="[0-9]*"
                        maxLength={1}
                        value={digit}
                        onChange={(event) => handleOtpChange(index, event.target.value)}
                        onKeyDown={(event) => handleOtpKeyDown(index, event)}
                        onPaste={handleOtpPaste}
                        required
                      />
                    </div>
                  ))}
                </div>
                <p className="jobtex-signup__otp-note">
                  Didn't receive code?{" "}
                  <button type="button" onClick={handleResend} disabled={resendSeconds > 0}>
                    Resend OTP{resendSeconds > 0 ? ` (00:${String(resendSeconds).padStart(2, "0")})` : ""}
                  </button>
                </p>
                <button className="jobtex-signup__submit" type="submit">
                  <span>Verify &amp; Complete Registration</span>
                  <TrustIcons name="arrow_forward" />
                </button>
                {otpMessage && <p className="jobtex-signup__status" role="status">{otpMessage}</p>}
                <button className="jobtex-signup__back" type="button" onClick={() => { setStep("signup"); setOtpMessage(""); }}>
                  <TrustIcons name="arrow_back" />
                  <span>Back to Sign Up (Edit Email)</span>
                </button>
              </form>
            </div>
          )}
        </div>

        <Trust />
      </FadeUp>
    </section>
    <JobNotifications />
    </>
  );
}
