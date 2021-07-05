package api.body;

import lombok.*;
import java.lang.String;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Simulacao {
    private String nome;
    private String cpf;
    private String email;
    private int valor;
    private int parcelas;
    private boolean seguro;
}
