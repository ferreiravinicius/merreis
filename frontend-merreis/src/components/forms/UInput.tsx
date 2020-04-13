import { TextField, TextFieldProps, FormControl } from "@material-ui/core";
import React from "react";

function UInput({ variant, ...props }: TextFieldProps): JSX.Element {
  return (
    <FormControl fullWidth>
      <TextField variant="outlined" {...props} />
    </FormControl>
  );
}

export default UInput;
