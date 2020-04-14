import { Container, Grid, TextField } from "@material-ui/core";
import React, { useState } from "react";
import { handleInputChange, handleSelectChange } from "../../commons/utils";
import UInput from "../../components/forms/UInput";
import USelect from "../../components/forms/USelect";
import { Group } from "../../types/models";
import UButton from "../../components/UButton";
import CheckOutlined from "@material-ui/icons/CheckOutlined";
import * as Yup from "yup";
import { Autocomplete, RenderInputParams } from "@material-ui/lab";
import UAutocomplete from "../../components/forms/UAutocomplete";

export interface Props {}

interface FormValues {
  group: Group | null;
  description: string;
  others: Group[];
}

const initialValues: FormValues = {
  group: null,
  description: "Teste",
  others: []
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
            getOptionLabel={(option: Group) => option.name}
            multiple={true}
            onChange={handleSelectChange({
              setter: setExpense,
              state: "others",
            })}
          />
{/* 
          <Autocomplete 
            value={expense.group}
            options={groups}
            getOptionLabel={(option: Group) => option.name}
            renderInput={(params: RenderInputParams) => (
              <TextField {...params} />
            )}
            onChange={handleSelectChange({
              setter: setExpense,
              state: "group",
            })}
          /> */}
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