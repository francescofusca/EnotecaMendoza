import os

# Configurazione
PROJECT_ROOT = '.'  # Directory principale del progetto (directory corrente)
OUTPUT_FILENAME = 'tutto_il_codice_react_vite_corretto.txt'

# Estensioni dei file da includere
INCLUDE_EXTENSIONS = (
    '.js', '.jsx', '.ts', '.tsx',
    '.css', '.scss', '.sass',
    '.html',
    '.json', # Includerà package.json, tsconfig.json, ecc.
    '.md',
    '.svg',
    '.mjs', '.cjs',
    # Aggiungi qui altre estensioni se necessario (es. .env.example se vuoi includere template di env)
    # Potresti voler escludere specifici file .json grandi come alcuni di configurazione di linter/formatter
    # se non sono strettamente "codice sorgente" per te.
)

# NOMI di Directory da escludere ovunque si trovino
# Questi nomi di cartella verranno esclusi ricorsivamente.
EXCLUDE_DIRS_BY_NAME = (
    'node_modules',
    '.git',
    'dist',
    'build',
    '.vscode',
    '.idea',
    '__pycache__',
    '.DS_Store',
    'coverage',
    '.vite',         # Cache di Vite
    # 'public',      # Valuta se vuoi includere la cartella public.
                     # Se index.html è qui, e vuoi includerlo, commenta questa riga.
                     # Altrimenti, se index.html è in root o src, puoi escludere 'public'.
    # Aggiungi altri nomi di directory qui
)

# File specifici da escludere (nomi di file esatti, indipendentemente dalla loro posizione)
EXCLUDE_FILES_BY_NAME = (
    OUTPUT_FILENAME,
    'yarn.lock', # Escludi i file .env che contengono segreti
    # Aggiungi altri nomi di file specifici, es:
    # 'specifico_file_grande.json',
)

def main():
    processed_files_count = 0
    with open(OUTPUT_FILENAME, 'w', encoding='utf-8', errors='ignore') as outfile:
        for root, dirs, files in os.walk(PROJECT_ROOT, topdown=True):
            # --- Correzione per l'esclusione delle directory ---
            # Modifica 'dirs' in-place per evitare che os.walk scenda in queste cartelle.
            # Viene fatto confrontando il NOME della directory con quelli in EXCLUDE_DIRS_BY_NAME.
            dirs[:] = [d for d in dirs if d not in EXCLUDE_DIRS_BY_NAME]

            for filename in files:
                # Escludi file specifici per nome
                if filename in EXCLUDE_FILES_BY_NAME:
                    continue

                if filename.endswith(INCLUDE_EXTENSIONS):
                    filepath = os.path.join(root, filename)
                    relative_filepath = os.path.relpath(filepath, PROJECT_ROOT)
                    
                    # --- Controllo aggiuntivo (failsafe) se il file è comunque in un percorso escluso ---
                    # Questo è utile se ci sono link simbolici o casi limite.
                    # Verifica se uno dei segmenti del percorso relativo è in EXCLUDE_DIRS_BY_NAME
                    # Normalizza i separatori a '/' per coerenza nello split
                    path_segments = relative_filepath.replace('\\', '/').split('/')
                    # Consideriamo i segmenti di directory (tutti tranne l'ultimo che è il nome del file)
                    directory_segments = path_segments[:-1]
                    
                    in_excluded_dir_path = False
                    for segment in directory_segments:
                        if segment in EXCLUDE_DIRS_BY_NAME:
                            in_excluded_dir_path = True
                            break
                    
                    if in_excluded_dir_path:
                        # print(f"Saltando (failsafe - percorso escluso): {relative_filepath}") # Per debug
                        continue

                    print(f"Aggiungendo: {relative_filepath}")
                    processed_files_count += 1
                    
                    outfile.write(f"--- INIZIO FILE: {relative_filepath} ---\n")
                    try:
                        with open(filepath, 'r', encoding='utf-8', errors='ignore') as infile:
                            # Leggi riga per riga per gestire meglio file molto grandi e newline
                            for line in infile:
                                outfile.write(line)
                        # Assicura un newline alla fine del contenuto del file se non c'è già
                        outfile.seek(0, os.SEEK_END)
                        if outfile.tell() > 0:
                            outfile.seek(outfile.tell() - 1)
                            if outfile.read(1) != '\n':
                                outfile.write("\n")
                    except Exception as e:
                        outfile.write(f"Errore durante la lettura del file {relative_filepath}: {e}\n")
                    outfile.write(f"--- FINE FILE: {relative_filepath} ---\n\n")

    print(f"\nCompletato! {processed_files_count} file sono stati scritti in {OUTPUT_FILENAME}")

if __name__ == '__main__':
    main()