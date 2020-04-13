import React from "react";
import { Formik, Form, FormikProps, Field } from "formik";
import { Container, Grid } from "@material-ui/core";
import UForm from "../../components/forms/UForm";

export interface Props {}

interface FormValues {
  description: string;
}

const initialValues: FormValues = {
  description: "",
};

const AnotherExpense: React.FC<Props> = () => {
  return (
    <Container maxWidth="md">
      
        <Formik
          initialValues={initialValues}
          onSubmit={(data: FormValues) => alert(JSON.stringify(data))}
        >
          {(props: FormikProps<FormValues>) => (
            <UForm>
              <Field name="description" type="text" />
              <button type="submit">Enviar</button>
            </UForm>
          )}
        </Formik>
    </Container>
  );
};

export default AnotherExpense;
