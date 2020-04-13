import { Container, Grid } from "@material-ui/core";
import { Formik, FormikProps } from "formik";
import React from "react";
import * as Yup from "yup";
import UForm from "../../components/forms/UForm";
import UInput from "../../components/forms/UInput";

export interface Props {}

interface FormValues {
  description: string;
  other?: string;
}

const initialValues: FormValues = {
  other: "",
  description: "",
};

const FormSchema = Yup.object().shape({
  other: Yup.string().min(3, "Description must have atleast 3 characters!"),
});

const AnotherExpense: React.FC<Props> = () => {
  return (
    <Container maxWidth="md">
      <Formik
        initialValues={initialValues}
        validationSchema={FormSchema}
        onSubmit={(data: FormValues) => alert(JSON.stringify(data))}
      >
        {(props: FormikProps<FormValues>) => (
          <UForm>
            <Grid item xs={12}>
              <UInput
                name="description"
                label="Description"
                autoComplete="off"
              />
            </Grid>
            <Grid item xs={12}>
              <UInput name="other" label="Other" autoComplete="off" />
            </Grid>
            <button type="submit">Enviar</button>
          </UForm>
        )}
      </Formik>
    </Container>
  );
};

export default AnotherExpense;
