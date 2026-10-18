import TrustIcons from "./TrustIcons";
import "../../../pages/sign_up/SignUp.css";

export default function Trust() {
  return (
    <div className="jobtex-signup__trust">
      <span>
        <TrustIcons name="verified_user" />
        256-Bit SSL Encrypted
      </span>
      <i aria-hidden="true" />
      <span>
        <TrustIcons name="support_agent" />
        24/7 Candidate Support
      </span>
      <i aria-hidden="true" />
      <span>
        <TrustIcons name="security" />
        GDPR Compliant
      </span>
    </div>
  );
}
