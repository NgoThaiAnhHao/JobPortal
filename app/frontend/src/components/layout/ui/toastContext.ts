import { createContext, useContext } from "react";
import type { ToastType } from "./Toast";

type ToastContextValue = {
  notify: (type: ToastType, message: string) => void;
};

export const ToastContext = createContext<ToastContextValue | null>(null);

export function useToast(): ToastContextValue {
  const context = useContext(ToastContext);
  if (!context) throw new Error("useToast must be used within ToastProvider");
  return context;
}
