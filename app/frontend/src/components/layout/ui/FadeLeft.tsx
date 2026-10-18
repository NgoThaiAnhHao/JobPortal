import { motion } from "motion/react";
import type { ReactNode } from "react";

type FadeUpProps = {
  children?: ReactNode;
  delay?: number;
  duration?: number;
  distance?: number;
  className?: string;
};

export default function FadeLeft({
  children,
  delay = 0,
  duration = 0.7,
  distance = 40,
  className = "",
}: FadeUpProps) {
  return (
    <motion.div
      initial={{ opacity: 0, x: distance }}
      whileInView={{ opacity: 1, x: 0 }}
      viewport={{ amount: 0.2 }}
      transition={{
        duration,
        delay,
        ease: "easeOut",
      }}
      className={className}
    >
      {children}
    </motion.div>
  );
}
