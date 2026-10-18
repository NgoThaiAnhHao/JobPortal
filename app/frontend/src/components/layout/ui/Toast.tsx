import styled from "styled-components";
import FadeLeft from "./FadeLeft";

export type ToastType = "success" | "error";

type ToastProps = {
  type: ToastType;
  message: string;
  onClose: () => void;
};

export default function Toast({ type, message, onClose }: ToastProps) {
  return (
    <StyledWrapper>
      <FadeLeft className="toast-container" aria-live="polite" aria-atomic="true">
        <div className={`toast-item ${type}`} role={type === "error" ? "alert" : "status"}>
          <div className="toast-content">
            <span className="toast-status-icon" aria-hidden="true">
              {type === "success" ? "✓" : "×"}
            </span>
            <span className="toast-text">{message}</span>
          </div>
          <button className="toast-close" type="button" aria-label="Dismiss notification" onClick={onClose}>
            <svg aria-hidden="true" fill="none" viewBox="0 0 24 24">
              <path stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18 17.94 6M18 18 6.06 6" />
            </svg>
          </button>
          <div className="toast-progress-bar" />
        </div>
      </FadeLeft>
    </StyledWrapper>
  );
}

const StyledWrapper = styled.div`
  .toast-container {
    position: fixed;
    top: 1.25rem;
    right: 1.25rem;
    z-index: 2000;
    width: min(24rem, calc(100vw - 2.5rem));
    margin: 0;
    padding: 0;
    list-style: none;
    font-family: sans-serif;
  }

  .toast-item {
    position: relative;
    display: flex;
    align-items: center;
    justify-content: space-between;
    gap: 1rem;
    overflow: hidden;
    padding: 0.9rem 1rem;
    border: 1px solid transparent;
    border-left-width: 4px;
    border-radius: 0.75rem;
    backdrop-filter: blur(12px);
    box-shadow: 0 10px 30px rgba(15, 23, 42, 0.14), 0 2px 6px rgba(15, 23, 42, 0.08);
    animation: toast-enter 220ms ease-out;
  }

  .toast-item.success {
    color: #166534;
    border-color: #bbf7d0;
    border-left-color: #16a34a;
    background: linear-gradient(135deg, #f0fdf4 0%, #dcfce7 100%);
    box-shadow: 0 10px 30px rgba(22, 101, 52, 0.14), 0 2px 6px rgba(22, 101, 52, 0.08);
  }

  .toast-item.error {
    color: #991b1b;
    border-color: #fecaca;
    border-left-color: #dc2626;
    background: linear-gradient(135deg, #fff7f7 0%, #fee2e2 100%);
    box-shadow: 0 10px 30px rgba(153, 27, 27, 0.14), 0 2px 6px rgba(153, 27, 27, 0.08);
  }

  .toast-content {
    display: flex;
    align-items: center;
    gap: 0.65rem;
    min-width: 0;
  }

  .toast-status-icon {
    display: grid;
    flex: 0 0 1.4rem;
    width: 1.4rem;
    height: 1.4rem;
    place-items: center;
    border: 2px solid currentColor;
    border-radius: 50%;
    font-weight: 700;
    line-height: 1;
  }

  .toast-text {
    font-size: 0.875rem;
    line-height: 1.4;
    overflow-wrap: anywhere;
  }

  .toast-close {
    display: grid;
    flex: 0 0 1.5rem;
    width: 1.5rem;
    height: 1.5rem;
    place-items: center;
    padding: 0;
    border: 0;
    border-radius: 0.25rem;
    color: inherit;
    background: transparent;
    cursor: pointer;
  }

  .toast-close:hover {
    background: rgba(15, 23, 42, 0.08);
  }

  .toast-close svg {
    width: 1rem;
    height: 1rem;
  }

  .toast-progress-bar {
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 3px;
    background: currentColor;
    transform-origin: left;
    animation: toast-progress 5s linear forwards;
  }

  @keyframes toast-progress {
    to { transform: scaleX(0); }
  }

  @keyframes toast-enter {
    from { opacity: 0; transform: translateY(-8px); }
    to { opacity: 1; transform: translateY(0); }
  }
`;
