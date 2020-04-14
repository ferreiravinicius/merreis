import { TextFieldProps } from "@material-ui/core";
import FormControl from "@material-ui/core/FormControl";
import TextField from "@material-ui/core/TextField";
import { FieldInputProps, useField } from "formik";
import React from "react";
interface Props {
  name: string;
}

type UInputProps<T> = Exclude<TextFieldProps, FieldInputProps<T>> & Props;

function UInput<T>({
  name,
  error,
  variant,
  helperText,
  ...props
}: UInputProps<T>): JSX.Element {
  const [field, { error: validationError }] = useField<T>(name);
  
  const existsValidationError: boolean = !!validationError;
  const existsError: boolean = error || existsValidationError;

  const extractMessage = (): any => {
    if (error && !!helperText) return helperText;
    if (existsValidationError) return validationError;
    return helperText;
  };

  return (
    <FormControl fullWidth>
      <TextField
        variant="outlined"
        error={existsError}
        helperText={extractMessage()}
        {...field}
        {...props}
      />
    </FormControl>
  );
}

export default UInput;
