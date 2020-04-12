import { TextField, TextFieldProps } from "@material-ui/core";
import React from "react";

function UInput({ variant, ...props }: TextFieldProps): JSX.Element {
  return <TextField variant="outlined" {...props} />;
};

export default UInput;
