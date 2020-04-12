import { ChangeEvent } from "react";

export const handleInputChange = <T>(
  setter: React.Dispatch<React.SetStateAction<T>>
) => (e: ChangeEvent<HTMLInputElement>) => {
  const typed = e.target.value;
  const field: string = e.target.name;
  if (field) setter((actual: T) => ({ ...actual, [field]: typed }));
};

export const handleSelectChange = <T>(
  setter: React.Dispatch<React.SetStateAction<T>>,
  state: string
) => (_: any, value: any) => {
  const treatedValue = value;
  setter((actual: T) => ({ ...actual, [state]: treatedValue }));
};
