import { Container } from "@material-ui/core";
import { Formik, FormikProps, useFormik } from "formik";
import React from "react";
import UForm from "../../components/forms/UForm";
import UInput from "../../components/forms/UInput";
import * as Yup from "yup";

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
            <UInput name="description" label="Description" autoComplete="off" />
            <button type="submit">Enviar</button>
          </UForm>
        )}
      </Formik>
    </Container>
  );
};

export default AnotherExpense;
