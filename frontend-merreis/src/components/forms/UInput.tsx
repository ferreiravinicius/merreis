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
  variant,
  helperText,
  ...props
}: UInputProps<T>): JSX.Element {
  const [field, { error }] = useField<T>(name);

  return (
    <FormControl fullWidth>
      <TextField
        variant="outlined"
        error={!!error}
        helperText={error || helperText}
        {...field}
        {...props}
      />
    </FormControl>
  );
}

export default UInput;
