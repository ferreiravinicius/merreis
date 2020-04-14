import { Container, Grid } from "@material-ui/core";
import { Formik, FormikProps } from "formik";
import React from "react";
import * as Yup from "yup";
import UForm from "../../components/forms/UForm";
import UInput from "../../components/forms/UInput";
import UAutocomplete from "../../components/forms/UAutocomplete";
import { Group } from "../../types/models";

export interface Props {}

interface FormValues {
  description: string;
  group: Group | null
}

const initialValues: FormValues = {
  description: "",
  group: null
};

const FormSchema = Yup.object().shape({
  description: Yup.string().min(3, "Description must have atleast 3 characters!"),
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
              <UAutocomplete 
                name="group"
                label="Group"
                options={groups}
                getOptionLabel={(option: Group) => option.name}
              />
            </Grid>
            <button type="submit">Enviar</button>
          </UForm>
        )}
      </Formik>
    </Container>
  );
};

const groups: Group[] = [
  { id: 1, name: "Lazer" },
  { id: 1, name: "Teste" },
  { id: 1, name: "Outros" },
];

export default AnotherExpense;
