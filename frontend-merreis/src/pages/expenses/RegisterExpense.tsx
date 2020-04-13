import { Container, Grid } from "@material-ui/core";
import React, { useState } from "react";
import { handleInputChange, handleSelectChange } from "../../commons/utils";
import UInput from "../../components/forms/UInput";
import USelect from "../../components/forms/USelect";
import { Group } from "../../types/models";
import UButton from "../../components/UButton";
import CheckOutlined from "@material-ui/icons/CheckOutlined";
import * as Yup from "yup";

export interface Props {}

interface FormValues {
  value: string;
  group: Group | null;
  description: string;
}

const initialValues: FormValues = {
  value: "",
  group: null,
  description: "",
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
            name="description"
            label="Description"
            value={expense.description}
            onChange={handleInputChange({ setter: setExpense })}
          />
        </Grid>

        <Grid item xs={12}>
          <UInput
            name="value"
            label="Value"
            value={expense.value}
            onChange={handleInputChange({ setter: setExpense })}
          />
        </Grid>

        <Grid item xs={12}>
          <USelect
            name="group"
            label="Group"
            value={expense.group}
            options={groups}
            getOptionLabel={(option: Group) => option.name}
            onChange={handleSelectChange({
              setter: setExpense,
              state: "group",
            })}
          />
        </Grid>

        <Grid item xs={12}>
          <UButton
            color="primary"
            variant="contained"
            startIcon={<CheckOutlined />}
            onClick={handleSubmit}
          >
            Salvar
          </UButton>
        </Grid>
      </Grid>
    </Container>
  );
};

const groups: Group[] = [{ id: 1, name: "Lazer" }];

export default RegisterExpense;
