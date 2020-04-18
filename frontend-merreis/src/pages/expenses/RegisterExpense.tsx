import { Container, Grid } from "@material-ui/core";
import CheckOutlined from "@material-ui/icons/CheckOutlined";
import React, { useState } from "react";
import * as Yup from "yup";
import UAutocomplete from "../../components/forms/UAutocomplete";
import UInput from "../../components/forms/UInput";
import UButton from "../../components/UButton";
import { Group } from "../../types/models";

export interface Props {}

interface FormValues {
  group: Group | null;
  description: string;
  others: Group[];
}

const initialValues: FormValues = {
  group: null,
  description: "Teste",
  others: [
    { id: 2, name: "Tanto" },
  ]
};

const FormSchema = Yup.object().shape({
  value: Yup.number()
    .required("Value is required!")
    .min(0, "Expense must be a positive value!"),
  group: Yup.object()
    .nullable()
    .required("Group must be selected!"),
  description: Yup.string()
    .min(3, "Description must have atleast 3 characters!")
});

const RegisterExpense: React.FunctionComponent<Props> = () => {
  const [expense, setExpense] = useState<FormValues>(initialValues);

  const handleSubmit = (): void  => {
    FormSchema.validate(expense, { abortEarly: false })
      .then(() => {
        console.log("YAY")
      })
      .catch((err) => {
        console.log(err.errors);
      });
  };

  return (
    <Container maxWidth="md">
      <Grid container spacing={1}>
        <Grid item xs={12}>
          <UInput 
            label="Description"
            value={expense.description}
            setter={{ action: setExpense, name: "description" }}
          />
        </Grid>

        <Grid item xs={12}>

          <UAutocomplete 
            value={expense.others}
            options={groups}
            multiple={true}
            getOptionLabel={(option: Group) => option.name}
            getOptionSelected={(option: Group, value: Group ) => option.id === value.id }
            setter={{ action: setExpense, name: "others" }}
          />
        </Grid>

        <Grid item xs={12}>
          <UButton
            color="primary"
            variant="contained"
            startIcon={<CheckOutlined />}
            onClick={() => console.log(expense)}
          >
            Salvar
          </UButton>
        </Grid>
      </Grid>
    </Container>
  );
};

const groups: Group[] = [
  { id: 1, name: "Lazer" },
  { id: 2, name: "Tanto" },
  { id: 3, name: "Faz" },
];

export default RegisterExpense;