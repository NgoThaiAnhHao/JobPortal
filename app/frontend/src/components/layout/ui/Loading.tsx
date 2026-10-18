import { Classic } from "@/components/loading-ui/classic";

export function Loading() {
  return (
    <div className="flex items-center gap-6 *:size-6">
      <Classic className="text-red-01" />
    </div>
  );
}
