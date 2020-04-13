import React from "react";
import { ButtonProps, FormControl, Button } from "@material-ui/core";


function UButton({ ...props }: ButtonProps): JSX.Element {
  return (
    <FormControl>
      <Button {...props} />
    </FormControl>
  );
};

export default UButton;