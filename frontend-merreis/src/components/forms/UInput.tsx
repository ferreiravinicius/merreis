import React, { useRef, useEffect } from "react";
import { useField } from "@unform/core";
import { TextField, TextFieldProps } from "@material-ui/core";

export interface Props {
  name: string
  onType?: (data: any) => void
}

type UInputProps = TextFieldProps & Props;

const UInput: React.FC<UInputProps> = ({ name, variant, label, onType, ...props }) => {
  const ref = useRef<HTMLInputElement>(null);

  const { fieldName, defaultValue, error, registerField } = useField(name);

  useEffect(() => {
    registerField({
      path: "value",
      name: fieldName,
      ref: ref.current,
    });
  }, [fieldName, registerField]);

  const handleChange = () => {
    if (onType) {
      onType(ref.current?.value);
    }
  };

  return (
    <>
      <TextField
        inputRef={ref}
        error={!!error}
        variant="outlined"
        label={label || name}
        onChange={handleChange}
        defaultValue={defaultValue}
        {...props}
      />
    </>
  );
};

export default UInput;
