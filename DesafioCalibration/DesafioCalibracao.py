soma = 0

for linha in open('calibration_text.txt'):
    primeiro_digito = next((char for char in linha if char.isdigit()), None)
    ultimo_digito = next((char for char in reversed(linha) if char.isdigit()), None)
    
    if primeiro_digito and ultimo_digito:
        soma += int(primeiro_digito + ultimo_digito)

print(soma)
