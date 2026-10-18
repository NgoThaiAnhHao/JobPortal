import { useCallback, useEffect, useRef, useState, type ReactNode } from "react";
import Toast, { type ToastType } from "./Toast";
import { ToastContext } from "./toastContext";

type ActiveToast = {
  type: ToastType;
  message: string;
  id: number;
};

export function ToastProvider({ children }: { children: ReactNode }) {
  const [toast, setToast] = useState<ActiveToast | null>(null);
  const nextId = useRef(0);

  const notify = useCallback((type: ToastType, message: string) => {
    nextId.current += 1;
    setToast({ type, message, id: nextId.current });
  }, []);

  useEffect(() => {
    if (!toast) return;
    const timeout = window.setTimeout(() => setToast(null), 5000);
    return () => window.clearTimeout(timeout);
  }, [toast]);

  const dismiss = useCallback(() => setToast(null), []);

  return (
    <ToastContext.Provider value={{ notify }}>
      {children}
      {toast && (
        <Toast key={toast.id} type={toast.type} message={toast.message} onClose={dismiss} />
      )}
    </ToastContext.Provider>
  );
}
