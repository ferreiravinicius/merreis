import { Grid, GridProps } from "@material-ui/core";
import { Form } from "formik";
import React from "react";

type UFormProps = GridProps;

const UForm: React.SFC<UFormProps> = (
  { children },
  { spacing = 1 }: UFormProps
) => {
  return (
    <Grid container spacing={spacing} component={Form}>
      {children}
    </Grid>
  );
};

export default UForm;
