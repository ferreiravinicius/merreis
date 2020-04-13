import { ChangeEvent } from "react";

interface InputChangeParams<T> {
  setter: React.Dispatch<React.SetStateAction<T>>;
  state?: string;
  converter?: (value: any) => any;
}

export const handleInputChange = <T>({
  state,
  setter,
  converter,
}: InputChangeParams<T>) => (e: ChangeEvent<HTMLInputElement>) => {
  const typed = e.target.value;
  const field = state || e.target.name;
  const value = converter ? converter(typed) : typed;

  if (field) setter((actual: T) => ({ ...actual, [field]: value }));
};

interface SelectChangeParams<T> {
  setter: React.Dispatch<React.SetStateAction<T>>;
  state: string;
}

export const handleSelectChange = <T>({
  state,
  setter,
}: SelectChangeParams<T>) => (_: any, value: any) => {
  const treatedValue = value;
  setter((actual: T) => ({ ...actual, [state]: treatedValue }));
};
